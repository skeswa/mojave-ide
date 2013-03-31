package com.darkside.mojave.web.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.phakama.vaadin.github.session.GitHubSession;

import com.darkside.mojave.domain.User;
import com.darkside.mojave.web.dal.exception.UnsuccessfulOperationException;

public class UserDAO {
	public static User provideUser(EntityManager em, GitHubSession session) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			TypedQuery<User> query = em.createQuery("select o from com.darkside.mojave.domain.User o where o.userName = :userName", User.class);
			query.setParameter("userName", session.getUser().getLogin());
			User result = query.getSingleResult();
			return result;
		} catch (NoResultException e) {
			User newUser = new User();
			newUser.setAvatarUrl(session.getUser().getAvatarUrl());
			newUser.setEmail(session.getUser().getEmail());
			newUser.setUserName(session.getUser().getLogin());
			newUser = em.merge(newUser);
			transaction.commit();
			return newUser;
		} catch (RuntimeException e) {
			transaction.rollback();
			throw e;
		} catch (Exception e) {
			transaction.rollback();
			throw new UnsuccessfulOperationException(e);
		}
	}
}
