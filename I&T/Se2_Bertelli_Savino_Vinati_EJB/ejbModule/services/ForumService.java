package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.Discussion;
import entities.Post;
import exceptions.DiscussionsRetrievalException;
import exceptions.PostsRetrievalException;

@Stateless
public class ForumService {
	@PersistenceContext(unitName = "BertelliSavinoVinatiProject")
	private EntityManager em;
	
	public ForumService() {
		
	}
	
	public List<Discussion> findAllDiscussions() 
			throws DiscussionsRetrievalException {
		List<Discussion> discussions = null;
		try {
			discussions = em.createNamedQuery("Discussion.findAll", Discussion.class).getResultList();
		}
		catch(PersistenceException e) {
			throw new DiscussionsRetrievalException("ERROR: Could not retrieve discussions.");
		}
		return discussions;
	}
	
	public List<Post> findAllPostOfDiscussion (int idDiscussion) throws PostsRetrievalException {
		List<Post> posts = null;
		try {
			posts = em.createNamedQuery("Post.findByDiscussion", Post.class).setParameter(1, idDiscussion).getResultList();
		}
		catch(PersistenceException e) {
			throw new PostsRetrievalException("ERROR: Could not retrieve posts of discussion.");
		}
		return posts;
	}
	
}
