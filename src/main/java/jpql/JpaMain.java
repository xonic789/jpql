package jpql;

import javax.persistence.*;

public class JpaMain {

        public static void main(String[] args) {
            EntityManagerFactory hello = Persistence.createEntityManagerFactory("hello");

            EntityManager em =  hello.createEntityManager();

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            try{
                Member member = new Member();
                member.setUsername("MemberName");
                member.setAge(10);
                em.persist(member);

                String singleResult = em.createQuery("select m from Member m where m.username = :username", String.class)
                        .setParameter("username", "MemberName")
                        .getSingleResult();
                System.out.println("singleResult = " + singleResult);


                tx.commit();
            }catch (Exception e){
                tx.rollback();
            }finally {
                em.close();
            }
            hello.close();

        }
    }



