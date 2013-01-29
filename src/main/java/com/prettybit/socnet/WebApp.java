package com.prettybit.socnet;

import com.google.common.collect.Lists;
import com.prettybit.socnet.container.Container;
import com.prettybit.socnet.rest.annotation.NewAccountValueFactoryProvider;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ApplicationHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;

import javax.inject.Inject;

/**
 * @author Pavel Mikhalchuk
 */
public class WebApp extends ResourceConfig {

    @Inject
    public WebApp(ApplicationHandler handler) {
        handler.registerAdditionalBinders(serviceBinder());
    }

    private Iterable<Binder> serviceBinder() {
        return Lists.<Binder>newArrayList(new ServiceBinder());
    }

    private class ServiceBinder extends AbstractBinder {

        @Override
        protected void configure() {
            bind(Container.class).to(ContainerLifecycleListener.class);
            bind(NewAccountValueFactoryProvider.class).to(ValueFactoryProvider.class);
        }

    }

}