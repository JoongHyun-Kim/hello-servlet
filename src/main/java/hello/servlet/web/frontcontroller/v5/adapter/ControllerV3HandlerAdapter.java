package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3); //ControllerV3 인터페이스를 구현한 무언가가 넘어오게 되면 True 반환. 다른 것을 구현한 것들은 전부 False 반환.
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        ControllerV3 controller = (ControllerV3) handler; //이미 ControllerV3만 걸러진 상태이기 때문에 casting해서 사용해도 된다.

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator() //HttpServletRequest서 모든 parameter name을 다 가져 하나씩 돌리면서
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName))); //name을 key로 하면서 request.getParameter로 모든 value 다 꺼내오기

        return paramMap;
    }

}
