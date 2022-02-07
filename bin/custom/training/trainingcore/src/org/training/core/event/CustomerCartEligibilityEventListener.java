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
package org.training.core.event;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.ForgottenPwdEvent;
import de.hybris.platform.commerceservices.model.process.ForgottenPasswordProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import org.springframework.beans.factory.annotation.Required;
import org.training.core.model.CustomerCartEligibilityEmailProcessModel;


/**
 * Listener for "forgotten password" functionality event.
 */
public class CustomerCartEligibilityEventListener extends AbstractAcceleratorSiteEventListener<CustomerCartEligibilityEvent>
{

	private ModelService modelService;
	private BusinessProcessService businessProcessService;


	protected BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	/**
	 * @return the modelService
	 */
	protected ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	@Override
	protected void onSiteEvent(final CustomerCartEligibilityEvent event)
	{
		final CustomerCartEligibilityEmailProcessModel customerCartEligibilityEmailProcessModel = (CustomerCartEligibilityEmailProcessModel) getBusinessProcessService()
				.createProcess("customerCartEligibility-" + event.getCart().getCode() + "-" + System.currentTimeMillis(),
						"customerCartEligibilityEmailProcess");
		customerCartEligibilityEmailProcessModel.setSite(event.getSite());
		customerCartEligibilityEmailProcessModel.setCustomer(event.getCustomer());
		customerCartEligibilityEmailProcessModel.setCart(event.getCart());
		customerCartEligibilityEmailProcessModel.setLanguage(event.getLanguage());
		customerCartEligibilityEmailProcessModel.setCurrency(event.getCurrency());
		customerCartEligibilityEmailProcessModel.setStore(event.getBaseStore());
		getModelService().save(customerCartEligibilityEmailProcessModel);
		getBusinessProcessService().startProcess(customerCartEligibilityEmailProcessModel);
	}

	@Override
	protected SiteChannel getSiteChannelForEvent(final CustomerCartEligibilityEvent event)
	{
		final BaseSiteModel site = event.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.site", site);
		return site.getChannel();
	}
}
