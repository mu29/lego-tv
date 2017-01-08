package mu29.legotv.common.flux;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * InJung Chung
 */

public class Dispatcher {

    private Subject<Action, Action> eventBus = new SerializedSubject<>(PublishSubject.create());

    void dispatch(Action action) {
        eventBus.onNext(action);
    }

    public Observable<Action> getObservable() {
        return eventBus;
    }

}