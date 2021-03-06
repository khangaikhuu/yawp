package io.yawp.repository.scanner;

import io.yawp.repository.EndpointFeatures;
import io.yawp.repository.actions.ActionKey;
import io.yawp.repository.actions.ActionMethod;

import java.util.HashMap;
import java.util.Map;

public class ActionLoader {

    private Map<Class<?>, Map<ActionKey, ActionMethod>> cache = new HashMap<>();

    public void load(EndpointFeatures<?> endpoint, EndpointTree<?> tree) {
        endpoint.setActions(tree.loadActions(cache));
    }
}
