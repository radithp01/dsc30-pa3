/*
  Name: Raditharan Prabhu
  PID:  A15771941
 */

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Messenger Application Test
 * @author Raditharan Prabhu
 * @since  A15771941
 */
public class MessengerApplicationTest {

    /*
      Messages defined in starter code. Remember to copy and paste these strings to the
      test file if you cannot directly access them. DO NOT change the original declaration
      to public.
     */
    private static final String INVALID_INPUT =
            "The source path given cannot be parsed as photo.";
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";
    private static final String DENIED_USER_GROUP =
            "This operation is disabled in your user group.";

    /*
      Global test variables. Initialize them in @Before method.
     */
    PremiumUser marina;
    MessageExchange room;
    StandardUser Radith;
    StandardUser amol;

    /*
      The date used in Message and its subclasses. You can directly
      call this in your test methods.
     */
    LocalDate date = LocalDate.now();

    /*
     * Setup
     */
    @Before
    public void setup() {
        marina = new PremiumUser("Marina", "Instructor");
        room = new ChatRoom();
    }

    /*
      Recap: Assert exception without message
     */
    @Test (expected = IllegalArgumentException.class)
    public void testPremiumUserThrowsIAE() {
        marina = new PremiumUser("Marina", null);

    }
    @Test (expected = IllegalArgumentException.class)
    public void IAEExceptions() {
        StandardUser Anishka = new StandardUser(null, "student");
        StandardUser radith =  new StandardUser("Prabhu", null);
        marina.setBio(null);
        try {
            marina.joinRoom(null);
        } catch (OperationDeniedException ode) {
            fail("Cannot catch");
        }
        Anishka.quitRoom(null);
        radith.createChatRoom(null);
        marina.createPhotoRoom(null);
        ChatRoom crTest = new ChatRoom();
        Anishka.sendMessage(crTest, MessageType.TEXT, "Hey");
        marina.fetchMessage(crTest);
        crTest.addUser(marina);
        marina.sendMessage(crTest, MessageType.TEXT, null);
        marina.setCustomTitle(null);
    }

    /*
      Assert exception with message
     */
    @Test
    public void testPhotoMessageThrowsODE() {
        try {
            PhotoMessage pm = new PhotoMessage(marina, "PA02.zip");
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }
    }

    /*
     * Assert message content without hardcoding the date
     */
    @Test
    public void testTextMessageGetContents() {
        try {
            TextMessage tm = new TextMessage(marina, "A sample text message.");

            // concatenating the current date when running the test
            String expected = "<Premium> Marina [" + date + "]: A sample text message.";
            assertEquals(expected, tm.getContents());
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    /*
      public
     */
    @Test
    public void testMessagesGetters() {
        try {
            PhotoMessage Photo1 = new PhotoMessage(marina,"PA03.jpg");
            assertEquals("jpg",Photo1.getExtension());
            StickerMessage Sticker1 = new StickerMessage(marina,"marina-collections-8/LOLFace");
            assertEquals("marina-collections-8",Sticker1.getPackName());
        } catch (Exception e){
            e.getMessage();
        }
    }
    @Test
    public void testConstructorsGetContents() {
        try {
            TextMessage Text1 = new TextMessage(marina,"DSC30 Tests");
            String expected = "<Premium> Marina [" + date + "]: DSC30 Tests";
            assertEquals(expected, Text1.getContents());
            PhotoMessage Photo1 = new PhotoMessage(marina,"PA03.jpg");
            String expected1 = "<Premium> Marina [" + date + "]: Picture at PA03.jpg";
            assertEquals(expected1, Photo1.getContents());
            StickerMessage Sticker1 = new StickerMessage(marina,"marina-collections-8/LOLFace");
            String expected2 = "<Premium> Marina [" + date + "]: Sticker LOLFace from Pack [marina-collections-8]";
            assertEquals(expected2,Sticker1.getContents());
        } catch (Exception e) {
            e.getMessage();
        }
    }


    @Test
    public void testMessagesThrowODEs() {
        try {
            PhotoMessage photo1 = new PhotoMessage(marina, "PA03.pdf");
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }
        try {
            Radith = new StandardUser("radith","Student");
            PhotoMessage photo1 = new PhotoMessage(Radith, "aaaaaaa.jpeg");
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(DENIED_USER_GROUP, ode.getMessage());
        }
        try {
            StringBuilder testText = new StringBuilder();
            for (int i = 0; i < 256; i++){
                testText.append("hi");
            }
            String testString = testText.toString();
            TextMessage text1 = new TextMessage(marina,testString);
            fail("Exception not thrown");
        }catch (OperationDeniedException ode) {
            assertEquals(EXCEED_MAX_LENGTH, ode.getMessage());
        }
        try {
            Radith = new StandardUser("radith","Student");
            StickerMessage sticker1 = new StickerMessage(Radith, "radith-collections-8/LOLFace");
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(DENIED_USER_GROUP, ode.getMessage());
        }
    }
    @Test
    public void testUserandMessageExchange() {
        PremiumUser amol = new PremiumUser("amolkhanna", "student");
        Radith = new StandardUser("prabhu", "cool");
        StandardUser anishka = new StandardUser("Bharg", "Econ student");
        assertEquals("Econ student",anishka.displayBio());
        anishka.setBio("Comp Student");
        assertEquals("Comp Student",anishka.displayBio());
        PremiumUser niyati = new PremiumUser("Shirguppi", "nus");
        assertEquals("nus",niyati.displayBio());
        niyati.setBio("DSC Student");
        assertEquals("DSC Student",niyati.displayBio());
        niyati.setCustomTitle("Student");
        assertEquals("<Student> Shirguppi",niyati.displayName());
        ChatRoom crTest = new ChatRoom();
        PhotoRoom prTest = new PhotoRoom();
        crTest.addUser(Radith);
        assertEquals(1,crTest.getUsers().size());
        crTest.addUser(amol);
        assertEquals(2,crTest.getUsers().size());
        crTest.addUser(niyati);
        assertEquals(3,crTest.getUsers().size());
        Radith.sendMessage(crTest,MessageType.TEXT,"hi");
        assertEquals(1,crTest.getLog().size());
        String outputExpected = "prabhu [2021-01-26]: hi" + "\r\n";
        assertEquals(outputExpected,Radith.fetchMessage(crTest));
        niyati.sendMessage(crTest,MessageType.PHOTO,"photo.jpeg");
        assertEquals(2,crTest.getLog().size());
        String outputExpected1 = "prabhu [2021-01-26]: hi" + "\r\n" +
                "<Student> Shirguppi [2021-01-26]: Picture at photo.jpeg" + "\r\n";
        assertEquals(outputExpected1,niyati.fetchMessage(crTest));
        amol.sendMessage(crTest,MessageType.STICKER,"radith-collections-8/LOLFace");
        String outputExpected2 = "prabhu [2021-01-26]: hi" + "\r\n" +
                "<Student> Shirguppi [2021-01-26]: Picture at photo.jpeg" + "\r\n" +
                "<Premium> amolkhanna [2021-01-26]: Sticker LOLFace from Pack [radith-collections-8]" +
                "\r\n";
        assertEquals(outputExpected2,amol.fetchMessage(crTest));
        assertEquals(3,crTest.getLog().size());
        crTest.removeUser(amol);
        crTest.removeUser(niyati);
        crTest.removeUser(Radith);
        assertEquals(0,crTest.getUsers().size());
        prTest.addUser(marina);
        assertEquals(1,prTest.getUsers().size());
        prTest.addUser(amol);
        assertEquals(2,prTest.getUsers().size());
        prTest.addUser(niyati);
        assertEquals(3,prTest.getUsers().size());
        marina.sendMessage(prTest,MessageType.TEXT,"hi");
        assertEquals(0,prTest.getLog().size());
        niyati.sendMessage(prTest,MessageType.PHOTO,"photo.jpeg");
        assertEquals(1,prTest.getLog().size());
        amol.sendMessage(prTest,MessageType.STICKER,"radith-collections-8/LOLFace");
        assertEquals(1,prTest.getLog().size());
        prTest.removeUser(amol);
        prTest.removeUser(niyati);
        prTest.removeUser(marina);
        assertEquals(0,prTest.getUsers().size());
        ArrayList<User> users1 = new ArrayList<>();
        users1.add(anishka);
        users1.add(marina);
        MessageExchange new1 = Radith.createChatRoom(users1);
        assertEquals(3,new1.getUsers().size());
        try {
            amol.joinRoom(new1);
            niyati.joinRoom(new1);
        }catch (Exception e) {
            e.getMessage();
        }
        assertEquals(5,new1.getUsers().size());
        amol.quitRoom(new1);
        Radith.quitRoom(new1);
        assertEquals(3,new1.getUsers().size());
        ArrayList<User> users2 = new ArrayList<>();
        MessageExchange new2 = Radith.createChatRoom(users2);
        assertEquals(1,new2.getUsers().size());
        try {
            amol.joinRoom(new2);
            niyati.joinRoom(new2);
            anishka.joinRoom(new2);
        }catch (Exception e) {
            e.getMessage();
        }
        assertEquals(4,new2.getUsers().size());
        amol.quitRoom(new2);
        Radith.quitRoom(new2);
        assertEquals(2,new2.getUsers().size());
        ArrayList<User> users3 = new ArrayList<>();
        users3.add(anishka);
        MessageExchange new3 = Radith.createChatRoom(users3);
        assertEquals(2,new3.getUsers().size());
        try {
            niyati.joinRoom(new3);
        }catch (Exception e) {
            e.getMessage();
        }
        assertEquals(3,new3.getUsers().size());
        niyati.quitRoom(new3);
        assertEquals(2,new3.getUsers().size());
        MessageExchange pr1 = amol.createPhotoRoom(users1);
        assertEquals(2,pr1.getUsers().size());
        try {
            niyati.joinRoom(pr1);
        }catch (Exception e) {
            e.getMessage();
        }
        assertEquals(3,pr1.getUsers().size());
        amol.quitRoom(pr1);
        assertEquals(2,pr1.getUsers().size());
    }
    @Test
    public void testExceptionsODEUsers() {
        StandardUser radith = new StandardUser("prabhu", "awesome");
        MessageExchange pr = new PhotoRoom();
        MessageExchange cr = new ChatRoom();
        try {
            radith.joinRoom(pr);
            fail("Exception thrown");
        } catch (OperationDeniedException ode) {
            assertEquals(User.JOIN_ROOM_FAILED, ode.getMessage());
        }
        try {
            PhotoMessage photo = new PhotoMessage(radith, "picture.jpeg");
            fail("Exception to be thrown");
        } catch (OperationDeniedException ode) {
            assertEquals(DENIED_USER_GROUP, ode.getMessage());
        }
    }
}
