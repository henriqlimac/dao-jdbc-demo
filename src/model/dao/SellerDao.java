package model.dao;

import model.entities.Seller;

import java.util.List;

public interface SellerDao {
    void insert(Seller payload);
    void update(Seller payload);
    void deleteById(Integer id);

    Seller findById(Integer id);
    List<Seller> findAll();
    List<Seller> findByDepartment(Integer id);
}
