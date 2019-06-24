package com.seewo.spider.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * 线程安全的JavaScriptEngine工厂类
 * 用于获得线程本地变量JavaScriptEngine
 *
 * @author qiuwuhui
 * @date 2019/6/13
 */
public class JSEngineFactory {
    private volatile static JSEngineFactory jsEngineFactory;
    private ThreadLocal<ScriptEngine> scriptEngineThreadLocal;
    private final static String JS_ENGINE_NAME = "nashorn";
    private final static String ENGINE_INIT_FILE = "/crypto-js.min.js";

    private JSEngineFactory() {
        scriptEngineThreadLocal = new ThreadLocal<>();
    }

    public static JSEngineFactory getInstance() {
        if (jsEngineFactory == null) {
            synchronized (JSEngineFactory.class) {
                if (jsEngineFactory == null) {
                    jsEngineFactory = new JSEngineFactory();
                }
            }
        }
        return jsEngineFactory;
    }

    public ScriptEngine getScriptEngine() {
        ScriptEngine scriptEngine;
        if ((scriptEngine = scriptEngineThreadLocal.get()) == null) {
            scriptEngine = new ScriptEngineManager().getEngineByName(JS_ENGINE_NAME);
            initEngine(scriptEngine);
            scriptEngineThreadLocal.set(scriptEngine);
        }
        return scriptEngine;
    }

    private void initEngine(ScriptEngine scriptEngine) {
        String path = JSEngineFactory.class.getResource(ENGINE_INIT_FILE).getPath();
        try {
            scriptEngine.eval(new FileReader(new File(path)));
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
