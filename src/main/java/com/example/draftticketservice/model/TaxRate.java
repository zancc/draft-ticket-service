package com.example.draftticketservice.model;

import com.example.draftticketservice.enums.TaxTypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
public class TaxRate {
    private final TaxTypeEnum taxType;
    private final Integer rate;

    public static Builder builder() {
        return new Builder();
    }

    public TaxRate(Builder b) {
        taxType = b.taxType;
        rate = b.rate;
    }

    public static final class Builder {
        private TaxTypeEnum taxType;
        private Integer rate;

        private Builder() {
        }

        public Builder taxType(TaxTypeEnum taxType) {
            this.taxType = taxType;
            return this;
        }

        public Builder rate(Integer rate) {
            this.rate = rate;
            return this;
        }

        public TaxRate build() {
            return new TaxRate(this);
        }
    }
}
