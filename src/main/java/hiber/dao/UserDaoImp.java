package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;


   @Override
   public void add(User user) {sessionFactory.openSession().save(user); }


   public User getCarUser(String model, int series){
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      TypedQuery<Car> query = session.createQuery("FROM Car car where car.model ='"+ model +"' AND car.series = "+series);
      Car newCar = query.getSingleResult();
      session.close();
      return newCar.getUser();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

//   @Override
//   public boolean equals(Object o){
//      if(o == null || o.getClass() != this.getClass()){
//         return false;
//      }
//      if(o.getClass() == User.class){
//         User equalsUser = (User) o;
//         return this.user.getId().equals(equalsUser.getId())
//             && (this.user.getFirstName().equals(equalsUser.getFirstName())
//             && this.user.getLastName().equals(equalsUser.getLastName())
//             && this.user.getEmail().equals(equalsUser.getEmail()));
//      }
//      return false;
//   }
}
