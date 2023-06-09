package com.albert.commerce.common.infra.sequenceGenerator;

import com.albert.commerce.common.infra.persistence.SequenceGenerator;
import org.springframework.stereotype.Component;

@Component
public class SequenceGeneratorImpl implements SequenceGenerator {

    @Override
    public String generate() {
        return new ObjectId().toHexString();
    }
}
