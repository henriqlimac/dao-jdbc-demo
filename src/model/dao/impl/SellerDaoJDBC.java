package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {
    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller payload) {
        
    }

    @Override
    public void update(Seller payload) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(
                "SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.id = ?"
            );

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Department department = instantiateDepartment(resultSet);
                Seller seller = instantiateSeller(resultSet, department);

                return seller;
            }

            return null;
        } catch (SQLException error) {
            throw new DbException(error.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    public List<Seller> findByDepartment(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Seller> sellers = new ArrayList<>();
        Map<Integer, Department> departmentsMap = new HashMap<>();

        try {
            statement = connection.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentId = department.Id "
                            + "WHERE DepartmentId = ? "
                            + "ORDER BY Name"
            );

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Department department = departmentsMap.get(resultSet.getInt("DepartmentId"));

                if(department == null) {
                    department = instantiateDepartment(resultSet);

                    departmentsMap.put(resultSet.getInt("DepartmentId"), department);
                }

                Seller seller = instantiateSeller(resultSet, department);

                sellers.add(seller);
            }

            return sellers;
        } catch (SQLException error) {
            throw new DbException(error.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }

    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();

        department.setId(resultSet.getInt("DepartmentId"));
        department.setName(resultSet.getString("DepName"));

        return department;
    }

    private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
        Seller seller = new Seller();

        seller.setId(resultSet.getInt("Id"));
        seller.setName(resultSet.getString("Name"));
        seller.setEmail(resultSet.getString("Email"));
        seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
        seller.setBirthdate(resultSet.getDate("BirthDate"));
        seller.setDepartment(department);

        return seller;
    }
}
