package mu29.legotv.common.flux;

import mu29.legotv.data.remote.RestClient;

/**
 * InJung Chung
 */

public class ActionCreator {

    protected RestClient service;
    private Dispatcher dispatcher;

    // TODO : 오류 발생하면 메시지 파싱해서 보여주자
    public ActionCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        service = RestClient.Factory.getApi();
    }

    protected void dispatch(String type, Object... data) {
        if (data.length % 2 != 0) {
            throw new IllegalArgumentException("데이터는 키/값이 쌍을 이루어야 합니다.");
        }

        Action.Builder builder = Action.type(type);
        for (int i = 0; i < data.length / 2; i++) {
            String key = (String) data[i * 2];
            Object value = data[i * 2 + 1];
            builder.bundle(key, value);
        }
        Action action = builder.build();
        dispatcher.dispatch(action);
    }

}
