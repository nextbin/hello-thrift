namespace java com.nextbin.hello.thrift.inf
namespace py nextbin.hello

struct User {
  1: i32 id;
  2: string name;
}

exception ServiceException {
}

struct Paging {
  1: i32 total;
  2: i32 pageNo;
  3: i32 pageSize;
  4: User list0;
}

service HelloService {
  list<Paging> error1(1: i32 pageNo, 2: i32 pageSize);

  list<Paging> error2(1: i32 pageNo, 2: i32 pageSize);

  void exp()
    throws (1: ServiceException ex1);

  list<User> getUsers(1: i32 pageNo, 2: i32 pageSize);

  string hello();

  i32 sum(1: i32 a, 2: i32 b);
}
