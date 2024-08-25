package com.example.smartgarage.repositories;

import com.example.smartgarage.exceptions.EntityNotFoundException;
import com.example.smartgarage.models.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CarRepositoryImpl implements CarRepository {

    private final SessionFactory sessionFactory;

    public CarRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Car> getUserCars(int userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Car> query = session.createQuery("from Car where owner.id = :userId", Car.class);
            query.setParameter("userId", userId);
            List<Car> cars = query.list();
            if (cars.isEmpty()) {
                throw new EntityNotFoundException("Cars", userId);
            }
            return cars;
        }
    }

    @Override
    public Car getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Car> query = session.createQuery("from Car where id = :carId", Car.class);
            query.setParameter("carId", id);
            List<Car> cars = query.list();
            if (cars.isEmpty()) {
                throw new EntityNotFoundException("Car", id);
            }
            return cars.get(0);
        }
    }

    @Override
    public void create(Car car) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(car);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Car car) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(car);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Car> getCars() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Car", Car.class).list();
        }
    }
}
