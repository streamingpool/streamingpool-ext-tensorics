// @formatter:off
/**
*
* This file is part of streaming pool (http://www.streamingpool.org).
* 
* Copyright (c) 2017-present, CERN. All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
* 
*/
// @formatter:on

package org.streamingpool.ext.tensorics.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.streamingpool.ext.tensorics.conf.TensoricsStreamingConfiguration;
import org.tensorics.core.resolve.engine.ResolvingEngine;
import org.tensorics.core.resolve.engine.ResolvingEngines;
import org.tensorics.core.resolve.resolvers.IterableResolvingExpressionResolver;
import org.tensorics.core.resolve.resolvers.PickResolver;
import org.tensorics.core.resolve.resolvers.PredicateConditionResolver;
import org.tensorics.core.resolve.resolvers.WindowedExpressionResolver;

@Configuration
@Import({ TensoricsStreamingConfiguration.class })
public class TestTensoricsEngineConfiguration {

    @Bean
    public ResolvingEngine createResolvingEngine() {
        return ResolvingEngines.defaultEngineWithAdditional(new PredicateConditionResolver<>(),
                new IterableResolvingExpressionResolver<>(), new WindowedExpressionResolver(), new PickResolver<>());
    }

}
