import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

void main() {
    Scanner scanner = new Scanner(System.in);
    int id;
    char choice;

    SellerDao sellerDao = DaoFactory.createSellerDao();

    Seller seller = sellerDao.findById(2);

    System.out.println("Test findById? (y/n)");
    choice = scanner.next().charAt(0);

    if(choice == 'y') {
        System.out.println("TESTE 1: seller findById");
        System.out.println(seller);
        System.out.println();
    }

    System.out.println("Test findByDepartment? (y/n)");
    choice = scanner.next().charAt(0);

    if(choice == 'y') {
        System.out.println("TESTE 2: seller findByDepartment");
        List<Seller> sellersDepartment = sellerDao.findByDepartment(2);

        for (Seller sd : sellersDepartment) {
            System.out.println(sd);
        }
        System.out.println();
    }

    System.out.println("Test findAll? (y/n)");
    choice = scanner.next().charAt(0);

    if(choice == 'y') {
        System.out.println("TESTE 3: seller findAll");
        List<Seller> sellersAll = sellerDao.findAll();

        for (Seller sa : sellersAll) {
            System.out.println(sa);
        }
        System.out.println();
    }

    System.out.println("Test insert? (y/n)");
    choice = scanner.next().charAt(0);

    if(choice == 'y') {
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
        System.out.println();
    }

    System.out.println("Test update? (y/n)");
    choice = scanner.next().charAt(0);

    if(choice == 'y') {
        System.out.println("TESTE 5: seller update");
        seller = sellerDao.findById(1);
        seller.setName("Henrique Lima");
        sellerDao.update(seller);
        System.out.println("Updated, new ID = " + seller.getId());
        System.out.println();
    }

    System.out.println("Test delete? (y/n)");
    choice = scanner.next().charAt(0);

    if(choice == 'y') {
        System.out.println("TESTE 6: seller deleteById");
        System.out.println("Enter ID for delete test or just press enter to skip: ");
        id = scanner.nextInt();

        if (id != 0) {
            sellerDao.deleteById(id);
            System.out.println("Delete completed");
        }
    }

    scanner.close();
}
