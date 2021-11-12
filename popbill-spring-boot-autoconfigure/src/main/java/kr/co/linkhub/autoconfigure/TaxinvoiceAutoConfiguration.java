package kr.co.linkhub.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.popbill.api.TaxinvoiceService;
import com.popbill.api.taxinvoice.TaxinvoiceServiceImp;

import kr.co.linkhub.autoconfigure.properties.TaxinvoiceProperties;

@Configuration
@ConditionalOnClass({ TaxinvoiceService.class })
@EnableConfigurationProperties(TaxinvoiceProperties.class)
public class TaxinvoiceAutoConfiguration {
    @Autowired
    private TaxinvoiceProperties taxinvoiceProperties;

    @Lazy
    @Bean(name = "TaxinvoiceService")
    @ConditionalOnMissingBean
    public TaxinvoiceService taxinvoiceServiceConfig() {
        TaxinvoiceService taxinvoiceService;

        TaxinvoiceServiceImp taxinvoiceServiceImp = new TaxinvoiceServiceImp();
        taxinvoiceServiceImp.setLinkID(taxinvoiceProperties.getLinkid());
        taxinvoiceServiceImp.setSecretKey(taxinvoiceProperties.getSecretkey());
        taxinvoiceServiceImp.setUseStaticIP(taxinvoiceProperties.isUsestaticip());
        taxinvoiceServiceImp.setUseGAIP(taxinvoiceProperties.isUsegaip());
        taxinvoiceServiceImp.setUseLocalTimeYN(taxinvoiceProperties.isUselocaltimeyn());
        taxinvoiceServiceImp.setIPRestrictOnOff(taxinvoiceProperties.isIprestrectonoff());

        taxinvoiceService = taxinvoiceServiceImp;

        return taxinvoiceService;
    }
}
