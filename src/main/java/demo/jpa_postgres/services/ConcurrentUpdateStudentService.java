//package demo.jpa_postgres.services;
//
//import demo.jpa_postgres.entities.Student;
//import demo.jpa_postgres.repositories.StudentDao;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class ConcurrentUpdateStudentService {
//  private final StudentDao studentDao;
//
//  @PersistenceContext
//  private EntityManager entityManager;
//
//  @Transactional(propagation = Propagation.REQUIRES_NEW)
//  public void transactionA() {
//
//    Long txId = ((Number) entityManager
//            .createNativeQuery("SELECT txid_current()")
//            .getSingleResult())
//            .longValue();
//
//    log.info("Transaction A, txId: {} - START", txId);
//
//    Student entityA = studentDao.findById(1L)
//            .orElseThrow();
//
//    log.info("Transaction A - Read entity: {}", entityA);
//    sleep(1000);
//
//    entityA.setName("Updated by Transaction A");
//    log.info("Transaction A - Start save & flush");
//    try {
//      studentDao.saveAndFlush(entityA);
//    } catch (Exception ex) {
//      log.error("Exception in transactionA: ", ex);
//    }
//
//    log.info("Transaction A - Updated entity");
//    log.info("Transaction A - END");
//  }
//
//  @Transactional(propagation = Propagation.REQUIRES_NEW)
//  public void transactionB() {
//
//    Long txId = ((Number) entityManager
//            .createNativeQuery("SELECT txid_current()")
//            .getSingleResult())
//            .longValue();
//
//    log.info("Transaction B, txId: {} - START", txId);
//
//    Student entityA = studentDao.findById(1L)
//            .orElseThrow();
//
//    log.info("Transaction B - Read entity: {}", entityA);
//
//    sleep(3000);
//    entityA.setName("Updated by Transaction B");
//    log.info("Transaction B - Start save & flush");
//    try {
//      studentDao.saveAndFlush(entityA);
//    } catch (Exception ex) {
//      log.error("Exception in transactionB: ", ex);
//    }
//
//    log.info("Transaction B - Updated entity");
//    log.info("Transaction B - END");
//  }
//
//  private void sleep(long millis) {
//    try {
//      Thread.sleep(millis);
//    } catch (InterruptedException e) {
//      Thread.currentThread().interrupt();
//      throw new RuntimeException(e);
//    }
//  }
//}
