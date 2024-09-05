package springboot.task_springboot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springboot.task_springboot.entity.User;

import java.util.List;

@Repository
    public class UserDaoImpl implements UserDao {

        @PersistenceContext
        private EntityManager entityManager;

        @Override
        @Transactional
        public void save(User user) {
            entityManager.persist(user);
        }

        @Override
        @Transactional
        public List<User> findAll() {
            List<User> users;
            TypedQuery<User> query = entityManager.createQuery("select u from User u order by u.id", User.class);
            users = query.getResultList();
            return users;
        }

        @Override
        @Transactional
        public User findById(long id) {
            User user = entityManager.find(User.class, id);
            entityManager.detach(user);
            return user;
        }

        @Override
        @Transactional
        public void update(User user) {
            entityManager.merge(user);
        }

        @Override
        @Transactional
        public void deleteById(long id) {
            User user = entityManager.find(User.class, id);
            entityManager.remove(user);
        }
    }

