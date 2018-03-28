import domain.Book;

//@RunWith(SpringJUnit4ClassRunner.class)
public class BookTest {


   // @Test
    public void testAppoint() {
        Book book = new Book();
        book.setName("haha");
        book.setNumber(12);
    }
}
