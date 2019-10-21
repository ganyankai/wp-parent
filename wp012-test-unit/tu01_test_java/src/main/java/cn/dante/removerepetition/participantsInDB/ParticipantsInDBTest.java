//package cn.dante.removerepetition.participantsInDB;
//
//import cn.dante.removerepetition.organization.Participant;
//import junit.framework.TestCase;
//
//class ParticipantsInDBTest extends TestCase {
//    ParticipantsInDB p;
//
//    Participant part1 = new Participant("ABC001", "Kent", "Tong", true, "Manager");
//    Participant part2 = new Participant("ABC003", "Paul", "Chan", true, "Manager");
//
//    void setUp() {
//        p = ParticipantsInDB.getInstance();
//    }
//
//    void tearDown() {
//        ParticipantsInDB.freeInstance();
//    }
//
//    void testAdd() {
////        Participant part1 = new Participant("ABC001", "Kent", "Tong", true, "Manager");
//        p.deleteAllParticipants();
//        p.addParticipant(part1);
//        assertEquals(p.getCount(), 1);
//    }
//
//    void testAdd2() {
//
//        p.deleteAllParticipants();
//        p.addParticipant(part1);
//        p.addParticipant(part2);
//        assertEquals(p.getCount(), 2);
//    }
//
//    void testEnum() {
////        Participant part1 = new Participant("ABC001", "Kent", "Tong", true, "Manager");
////        Participant part2 = new Participant("ABC003", "Paul", "Chan", true, "Manager");
//        p.deleteAllParticipants();
//        p.addParticipant(part2);
//        p.addParticipant(part1);
//        ParticipantEnumeratorById penum = new ParticipantEnumeratorById();
//        assertTrue(penum.next());
//        assertTrue(penum.get().equals(part1));
//        assertTrue(penum.next());
//        assertTrue(penum.get().equals(part2));
//        assertTrue(!penum.next());
//        penum.close();
//    }
//}