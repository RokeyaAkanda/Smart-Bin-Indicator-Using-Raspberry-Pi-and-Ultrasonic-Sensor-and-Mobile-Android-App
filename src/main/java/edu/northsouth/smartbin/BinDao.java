package edu.northsouth.smartbin;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class BinDao {
    public boolean save(BinModel binModel) {
        try {
            System.out.println(binModel.toString());
            Session session = HibernateUtil.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.save(binModel);
            transaction.commit();
            session.close();
            System.out.println("Model Saved Successfully");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
