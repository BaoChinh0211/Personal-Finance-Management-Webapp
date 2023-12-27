package bc.dao;

import bc.entity.UsersEntity;
import bc.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class UserDAO implements DAO<UsersEntity> {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    public static UserDAO getInstance() {
        return new UserDAO();
    }

    @Override
    public boolean insert(UsersEntity usersEntity) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Bắt đầu một transaction
            transaction = session.beginTransaction();

            // Thực hiện các thay đổi trên cơ sở dữ liệu
            session.persist(usersEntity);

            // Commit transaction để đồng bộ dữ liệu với cơ sở dữ liệu
            transaction.commit();

            // Không cần gọi session.flush() vì commit sẽ tự động đồng bộ

            // Print the object sau khi đồng bộ
            System.out.println(usersEntity);

            return true;
        } catch (HibernateException e) {
            // Nếu có lỗi, hủy bỏ transaction
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("An error occurred", e);
            return false;
        }
    }



    @Override
    public boolean update(UsersEntity usersEntity) {
        return false;
    }

    @Override
    public boolean delete(UsersEntity usersEntity) {
        return false;
    }

    @Override
    public ArrayList<UsersEntity> selectAll() {
        return null;
    }

    public UsersEntity searchUserByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            String hql = "FROM UsersEntity u WHERE u.username = :username AND u.password = :password";
            String hql = "FROM UsersEntity u WHERE u.username = :username";
            Query<UsersEntity> query = session.createQuery(hql, UsersEntity.class);
            query.setParameter("username", username);
//            query.setParameter("password", password);
            return query.uniqueResult();
        } catch (HibernateException e) {
            // Xử lý lỗi nếu cần
            logger.error("An error occured", e);
            return null;
        }
    }

    public boolean checkEmailExistence(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(*) FROM UsersEntity u WHERE u.email = :email";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("email", email);
            Long count = query.uniqueResult();
            return count > 0;
        } catch (HibernateException e) {
            logger.error("An error occurred", e);
            return false;
        }
    }

}
