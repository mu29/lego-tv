package mu29.legotv.action;

import mu29.legotv.common.flux.ActionCreator;
import mu29.legotv.common.flux.Dispatcher;

/**
 * InJung Chung
 */

public class VideoAction extends ActionCreator {

    // 프로그램 목록 받아오기
    public static final String PROGRAM_LIST_SUCCESS = "PROGRAM_LIST_SUCCESS";
    public static final String PROGRAM_LIST_FAIL = "PROGRAM_LIST_FAIL";

    public VideoAction(Dispatcher dispatcher) {
        super(dispatcher);
    }

    // 프로그램 목록 받아오기
    public void fetchProgramList() {
        service.fetchProgramList().subscribe(
            programs -> dispatch(PROGRAM_LIST_SUCCESS, "programs", programs),
            e -> dispatch(PROGRAM_LIST_FAIL)
        );
    }

}
