package com.dev.theatre.dao.impl;

import com.dev.theatre.dao.PerformanceSessionDao;
import com.dev.theatre.exception.DataProcessingException;
import com.dev.theatre.model.PerformanceSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PerformanceSessionDaoImpl implements PerformanceSessionDao {
    private final SessionFactory sessionFactory;

    public PerformanceSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PerformanceSession> findAvailableSessions(Long performanceId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<PerformanceSession> sessionQuery = session.createQuery("from PerformanceSession "
                    + "where id = :performanceId and DATE_FORMAT(showTime,'%Y-%m-%d') "
                    + "= :date", PerformanceSession.class);
            sessionQuery.setParameter("performanceId", performanceId);
            sessionQuery.setParameter("date", date.format(DateTimeFormatter.ISO_DATE));
            return sessionQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find available session from id "
                    + performanceId + " and Local date time: " + date, e);
        }
    }

    @Override
    public PerformanceSession add(PerformanceSession session) {
        Session currentSession = null;
        Transaction transaction = null;
        try {
            currentSession = sessionFactory.openSession();
            transaction = currentSession.beginTransaction();
            currentSession.save(session);
            transaction.commit();
            return session;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't create performance session " + session, e);
        } finally {
            if (currentSession != null) {
                currentSession.close();
            }
        }
    }

    @Override
    public PerformanceSession update(PerformanceSession performanceSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(performanceSession);
            transaction.commit();
            return performanceSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update performance session "
                    + performanceSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            PerformanceSession performanceSession = session.load(PerformanceSession.class, id);
            session.delete(performanceSession);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't delete performance session from id" + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<PerformanceSession> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(PerformanceSession.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get performance session from id " + id, e);
        }
    }
}
