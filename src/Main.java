import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

void main() {
    SellerDao sellerDao = DaoFactory.createSellerDao();

    System.out.println("TESTE 1: seller findById");
    Seller seller = sellerDao.findById(2);
    System.out.println(seller);
    System.out.println();

    System.out.println("TESTE 2: seller findByDepartment");
    List<Seller> sellersDepartment = sellerDao.findByDepartment(2);

    for (Seller sd : sellersDepartment) {
        System.out.println(sd);
    }
    System.out.println();

    System.out.println("TESTE 3: seller findAll");
    List<Seller> sellersAll = sellerDao.findAll();

    for (Seller sa : sellersAll) {
        System.out.println(sa);
    }
    System.out.println();

    System.out.println("TESTE 4: seller insert");
    Department department = new Department(2, null);
    Seller newSeller = new Seller(
            null,
            "Greg",
            "greg@gmail.com",
            new Date(),
            4000.0,
            department
    );
    sellerDao.insert(newSeller);

    System.out.println("Inserted, new ID = " + newSeller.getId());
}
