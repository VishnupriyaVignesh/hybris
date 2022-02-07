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
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.commerceservices.event.ForgottenPwdEvent;
import de.hybris.platform.commerceservices.model.process.ForgottenPasswordProcessModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.BaseStoreModel;
import org.springframework.beans.factory.annotation.Required;


/**
 * Listener for "forgotten password" functionality event.
 */
public class CustomerCartEligibilityEvent extends AbstractCommerceUserEvent
{

	private AbstractOrderModel cart;

	public AbstractOrderModel getCart() {
		return cart;
	}

	public void setCart(AbstractOrderModel cart) {
		this.cart = cart;
	}

	public CustomerCartEligibilityEvent(final AbstractOrderModel cart, final BaseSiteModel baseSiteModel, final BaseStoreModel baseStoreModel,
										final CurrencyModel currencyModel)
	{
		this.cart=cart;
		setBaseStore(baseStoreModel);
		setSite(baseSiteModel);
		setCurrency(currencyModel);
		setLanguage(cart.getUser().getSessionLanguage());

	}

}
