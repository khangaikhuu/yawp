package io.yawp.driver.api;

import io.yawp.repository.Repository;

public interface Driver {

    void init(Repository r);

    String name();

    PersistenceDriver persistence();

    QueryDriver query();

    NamespaceDriver namespace();

    TransactionDriver transaction();

    EnvironmentDriver environment();

    HelpersDriver helpers();

    PipesDriver pipes();
}
