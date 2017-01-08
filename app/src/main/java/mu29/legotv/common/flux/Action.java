package mu29.legotv.common.flux;

import java.util.HashMap;

/**
 * InJung Chung
 */

@SuppressWarnings("unchecked")
public class Action {

    private final String type;
    private final HashMap<String, Object> data;

    private Action(String type, HashMap<String, Object> data) {
        this.type = type;
        this.data = data;
    }

    public static Builder type(String type) {
        return new Builder(type);
    }

    public String getType() {
        return type;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public <T> T get(String key) {
        return (T) data.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Action)) return false;

        Action action = (Action) o;

        if (!type.equals(action.type)) return false;
        return !(data != null ? !data.equals(action.data) : action.data != null);
    }

    public static class Builder {

        private String type;
        private HashMap<String, Object> data;

        Builder(String type) {
            if (type == null) {
                throw new IllegalArgumentException("액션 타입이 정의되어야 합니다.");
            }
            this.type = type;
            this.data = new HashMap<>();
        }

        public Builder bundle(String key, Object value) {
            if (key == null) {
                throw new IllegalArgumentException("데이터의 key 는 null 일 수 없습니다.");
            }
            data.put(key, value);
            return this;
        }

        public Action build() {
            return new Action(type, data);
        }

    }

}