import model.entities.Department;
import model.entities.Seller;

void main() {
    Department obj = new Department(1, "Books");

    Seller seller = new Seller(40, "John", "bob@gmail.com", new Date(), 3000.0, obj);

    System.out.println(seller);
}
