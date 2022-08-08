package hello.servlet.domain.member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않기 때문에 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용해 하나만 사용되도록

    private static final MemberRepository instance = new MemberRepository(); //싱글톤

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() { //싱글톤일 때는 private으로 막아 아무나 생성하지 못하도록 해주기
    }

    //회원 저장
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    //Id로 회원 조회
    public Member findById(Long id) {
        return store.get(id);
    }

    //회원 전체 조회
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store에 있는 모든 값들을 꺼내 새로운 ArrayList에 담아 return
        //밖에서 ArrayList에 값을 넣거나 조작해도 store에 있는 값을 건들고 싶지 않기 때문
    }

    //store clear (테스트 사용)
    public void clearStore() {
        store.clear();
    }
}