package thread.collection.simple.list;


//프록시 = 대리자
public class SyncProxyList implements SimpleList{
    /**
     * 원본 대상을 가르키는 `target` 변수를 포함하고 있다. 이 변수는 `BasicList(x001)` 의 참조를 보관 한다.
     *
     *
     * **접근 제어**: 실제 객체에 대한 접근을 제한하거나 통제할 수 있다.
     * **성능 향상**: 실제 객체의 생성을 지연시키거나 캐싱하여 성능을 최적화할 수 있다.
     * **부가 기능 제공**: 실제 객체에 추가적인 기능(로깅, 인증, 동기화 등)을 투명하게 제공할 수 있다.
     */
    private SimpleList target;

    public SyncProxyList(SimpleList target) {
        this.target = target;
    }

    @Override
    public synchronized int size() {
        return target.size();
    }

    @Override
    public synchronized void add(Object e) {
        target.add(e);
    }

    @Override
    public synchronized Object get(int index) {
        return target.get(index);
    }

    @Override
    public String toString() {
        return target.toString() + " by " + this.getClass().getSimpleName();
    }
}
