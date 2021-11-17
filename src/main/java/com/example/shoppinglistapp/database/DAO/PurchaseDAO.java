package com.example.shoppinglistapp.database.DAO;

import com.example.shoppinglistapp.database.entity.Purchase;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class PurchaseDAO {
    private Session session;

    public PurchaseDAO(Session session) {
        this.session = session;
    }

    public List<Purchase> get(String sessionId) {
        Criteria criteria = session.createCriteria(Purchase.class);
        return criteria.add(Restrictions.eq("sessionId", sessionId)).list();
    }

    public Purchase get(Long purchaseId) {
        Criteria criteria = session.createCriteria(Purchase.class);
        return (Purchase) criteria.add(Restrictions.eq("id", purchaseId)).uniqueResult();
    }

    public long insertPurchase(Purchase purchase) {
        return (Long) session.save(purchase);
    }

    public void deletePurchase(Purchase purchase) {
        session.delete(purchase);
    }
}
