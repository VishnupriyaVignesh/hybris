/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package org.training.facades.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;
import org.training.core.model.CustomerCartEligibilityEmailProcessModel;


/**
 * Velocity context for a customer email.
 */
public class CustomerCartEligibilityEmailContext extends AbstractEmailContext<CustomerCartEligibilityEmailProcessModel>
{
	@Override
	public void init(final CustomerCartEligibilityEmailProcessModel customerCartEligibilityEmailProcessModel, final EmailPageModel emailPageModel)
	{
		super.init(customerCartEligibilityEmailProcessModel, emailPageModel);
		put(EMAIL,getCustomerEmailResolutionService().getEmailForCustomer(getCustomer(customerCartEligibilityEmailProcessModel)));
		put(DISPLAY_NAME,getCustomer(customerCartEligibilityEmailProcessModel).getDisplayName());
	}

	@Override
	protected BaseSiteModel getSite(CustomerCartEligibilityEmailProcessModel businessProcessModel) {
		return businessProcessModel.getCart().getSite();
	}

	@Override
	protected CustomerModel getCustomer(CustomerCartEligibilityEmailProcessModel businessProcessModel) {
		return (CustomerModel) businessProcessModel.getCart().getUser();
	}

	@Override
	protected LanguageModel getEmailLanguage(CustomerCartEligibilityEmailProcessModel businessProcessModel) {
		return businessProcessModel.getCart().getUser().getSessionLanguage();
	}
}
