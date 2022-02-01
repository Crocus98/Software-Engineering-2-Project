package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entities.Discussion;
import entities.Post;
import entities.User;
import exceptions.DiscussionCreationException;
import exceptions.DiscussionsRetrievalException;
import exceptions.InsertPostException;
import exceptions.PostsRetrievalException;

@Stateless
public class ForumService {
	@PersistenceContext(unitName = "BertelliSavinoVinatiProject")
	private EntityManager em;
	@EJB(name = "services/UserService")
	private UserService userService;

	public ForumService() {

	}

	public List<Discussion> getAllDiscussions() throws DiscussionsRetrievalException {
		List<Discussion> discussions = null;
		try {
			discussions = em.createNamedQuery("Discussion.findAll", Discussion.class).getResultList();
		} catch (PersistenceException e) {
			throw new DiscussionsRetrievalException(
					"[DiscussionsRetrievalException] ERROR: Could not retrieve discussions.");
		}
		return discussions;
	}

	public List<Post> getAllPostsOfDiscussion(int idDiscussion) throws PostsRetrievalException {
		List<Post> posts = null;
		try {
			posts = em.createNamedQuery("Post.findByDiscussion", Post.class).setParameter(1, idDiscussion)
					.getResultList();
		} catch (PersistenceException e) {
			throw new PostsRetrievalException(
					"[PostsRetrievalException] ERROR: Could not retrieve posts of discussion.");
		}
		return posts;
	}

	public User createDiscussion(User user, String title, String postComment) throws DiscussionCreationException {
		user = userService.getUserById(user.getId());
		Post post = new Post(user, postComment);
		Discussion discussion = new Discussion(title, user, post);
		if (user.getDiscussions() == null) {
			user.setDiscussions(new ArrayList<Discussion>());
		}
		user.addDiscussion(discussion);
		try {
			em.merge(user);
			em.flush();
			em.refresh(user);
		} catch (PersistenceException e) {
			throw new DiscussionCreationException("[DiscussionCreationException] Could not create new discussion.");
		}
		return user;
	}

	public Discussion getDiscussionById(int id) {
		return em.find(Discussion.class, id);
	}

	public User insertPost(User user, String comment, int idDiscussion) throws InsertPostException {
		Discussion discussion = getDiscussionById(idDiscussion);
		Post post = new Post(user, comment);
		if(discussion == null) {
			throw new InsertPostException("[InsertPostException] The discussion to which the post should belong does not exist.");
		}
		discussion.addPost(post);
		try {
			em.merge(discussion);
			em.flush();
		} catch (PersistenceException e) {
			throw new InsertPostException("[InsertPostException] Could not insert new post.");
		}
		user = userService.getUserById(user.getId());
		em.refresh(user);
		return user;
	}

}
