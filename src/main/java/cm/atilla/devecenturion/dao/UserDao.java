package cm.atilla.devecenturion.dao;

import cm.atilla.devecenturion.connection.HibernateUtil;
import cm.atilla.devecenturion.entiy.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDao {

    private Session session;
    private Transaction tx;

    public UserDao() {

        session = new HibernateUtil().getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    public boolean addUser(User user) {
        try {
            session.save(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }finally {
            session.close();
        }
    }

    public boolean updateUser(User user){
        session.update(user);
        tx.commit();
        return true;
    }

    public boolean deleteUser(User user){
        session.delete(user);
        tx.commit();
        return true;
    }
}


