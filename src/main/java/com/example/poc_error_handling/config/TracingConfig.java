/* (C)2024 */
package com.example.poc_error_handling.config;

import brave.baggage.BaggageField;
import brave.baggage.CorrelationScopeConfig;
import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.CurrentTraceContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {
    @Bean(name = "idToPropagate01")
    public BaggageField idToPropagate01() {
        return BaggageField.create("idToPropagate01");
    }

    @Bean(name = "idToPropagate02")
    public BaggageField idToPropagate02() {
        return BaggageField.create("idToPropagate02");
    }

    @Bean
    public CurrentTraceContext.ScopeDecorator mdcScopeDecorator(
            @Qualifier("idToPropagate01") BaggageField idToPropagate01,
            @Qualifier("idToPropagate02") BaggageField idToPropagate02) {
        return MDCScopeDecorator.newBuilder()
                .clear()
                .add(
                        CorrelationScopeConfig.SingleCorrelationField.newBuilder(idToPropagate01)
                                .flushOnUpdate()
                                .build())
                .add(
                        CorrelationScopeConfig.SingleCorrelationField.newBuilder(idToPropagate02)
                                .flushOnUpdate()
                                .build())
                .build();
    }
}
