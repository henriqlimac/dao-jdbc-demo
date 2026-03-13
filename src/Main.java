import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

void main() {
    SellerDao sellerDao = DaoFactory.createSellerDao();

    System.out.println("TESTE 1: seller findById");
    Seller seller = sellerDao.findById(2);

    System.out.println(seller);
}
