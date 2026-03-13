import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

void main() {
    SellerDao sellerDao = DaoFactory.createSellerDao();

    Seller seller = sellerDao.findById(2);

    System.out.println(seller);
}
