//package com.example.smartgarage.repositories;
//
//import com.example.smartgarage.exceptions.EntityNotFoundException;
//import com.example.smartgarage.models.User;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//import java.util.List;
//
//
//public class UserRepositoryImpl {
//
//    private final SessionFactory sessionFactory;
//
//
//    public UserRepositoryImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Override
//    public List<User> getUsers() {
//        try (Session session = sessionFactory.openSession()) {
//            List<User> list = session.createQuery("from User", User.class).list();
//            if (list.isEmpty()) {
//                throw new EntityNotFoundException("User", 0);
//            }
//            return list;
//        }
//    }
//
//    @Override
//    public User getById(int id) {
//        try (Session session = sessionFactory.openSession()) {
//            User user = session.get(User.class, id);
//            if (user == null) {
//                throw new EntityNotFoundException("User", id);
//            }
//            return user;
//        }
//    }
//
//    @Override
//    public User getByUsername(String username) {
//        try(Session session = sessionFactory.openSession()) {
//            List<User> result = session.createQuery("from User where username = :username", User.class)
//                    .setParameter("username", username)
//                    .list();
//            if (result.isEmpty()) {
//                throw new EntityNotFoundException("User", "username", username);
//            }
//            return result.get(0);
//        }
//    }
//
//    @Override
//    public User getByEmail(String email) {
//        try(Session session = sessionFactory.openSession()) {
//            List<User> result = session.createQuery("from User where email = :email", User.class)
//                    .setParameter("email", email)
//                    .list();
//            if (result.isEmpty()) {
//                throw new EntityNotFoundException("User", "email", email);
//            }
//            return result.get(0);
//        }
//    }
//
//    @Override
//    public User getByPhoneNumber(String phoneNumber) {
//        try(Session session = sessionFactory.openSession()) {
//            List<User> result = session.createQuery("from User where phoneNumber = :phoneNumber", User.class)
//                    .setParameter("phoneNumber", phoneNumber)
//                    .list();
//            if (result.isEmpty()) {
//                throw new EntityNotFoundException("User", "phoneNumber", phoneNumber);
//            }
//            return result.get(0);
//        }
//    }
//
//    @Override
//    public User create(User user) {
//
//        try(Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            session.persist(user);
//            session.getTransaction().commit();
//            return user;
//        }
//    }
//
//    @Override
//    public User update(int id, User updatedUser) {
//        try(Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            User user = session.get(User.class, id);
//            if (user == null) {
//                throw new EntityNotFoundException("User", id);
//            }
//            user.setUsername(updatedUser.getUsername());
//            user.setPassword(updatedUser.getPassword());
//            user.setEmail(updatedUser.getEmail());
//            user.setEmployee(updatedUser.isEmployee());
//            user.setPhoneNumber(updatedUser.getPhoneNumber());
//            session.merge(user);
//            session.getTransaction().commit();
//            return user;
//        }
//    }
//
//    @Override
//    public void delete(int id) {
//        try(Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            User user = session.get(User.class, id);
//            if (user == null) {
//                throw new EntityNotFoundException("User", id);
//            }
//            session.remove(user);
//            session.getTransaction().commit();
//        }
//    }
//}