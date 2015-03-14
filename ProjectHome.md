# Java Black-Scholes valuation engine #

Author: Michael Bret (mBret) Blackford

Unable to find any well documented Java source code for the Black-Scholes option model I decided to write my own.  This project contains the following Java classes which generate a mark-to-model (MtM):

  1. **[BlackScholesFormula](http://code.google.com/p/black-scholes/source/browse/trunk/OptionValuation/src/mBret/options/BlackScholesFormula.java)**: this class attempts to clearly layout the Black-Scholes model as expressed in the formula.  Each step is defined.
    * the **_calculate()_** method will return the a double with the calculated MtM
    * the **_calculateWithGreeks()_** will return the MtM value along with the greeks (delta, gamma, rho, theta, and vega)
  1. **[BlackScholes\_Abbreviated](https://code.google.com/p/black-scholes/source/browse/trunk/OptionValuation/src/mBret/options/BlackScholes_abbreviated.java)**: this class obfuscates the Black-Scholes equation.
    * **_blackScholesCall()_** returns the MtM as a double.  Relies on the Apache Common Math Library for the NormalDistributionImpl class.
  1. **[Black\_76](https://code.google.com/p/black-scholes/source/browse/trunk/OptionValuation/src/mBret/options/Black_76.java)**: this class implements the Black 76 option model.
    * **_Black76()_** method returns a double with the MtM.

.

Useful information was found at the following websites …

  * BlackScholes.java by Robert Sedgewick and Kevin Wayne -- http://introcs.cs.princeton.edu/java/22library/BlackScholes.java.html
  * _A decomposed implementation in Java_ by Dhruba Bandopadhyay … http://dhruba.name/2012/08/22/the-black-scholes-algorithm-a-decomposed-implementation-in-java/
  * _Black-Scholes in Multiple Languages_ … http://cseweb.ucsd.edu/~goguen/courses/130/SayBlackScholes.html
  * ~~Jernej Kovse … http://www1.relacija.com/?p=57~~

In addition to the Black-Scholes classes described above I also wrote some code to extract data from a Db (using jdbc, which requires the ojdbc.jar). The Black-Scholes classes are then used to calculate the MtM for the data in the extracted result set.  Output is written to Excel using the [Apache POI](http://poi.apache.org/) (which requires the poi-3.9-20121203.jar).


---


#### The input parameters to the Black-Scholes option valuation model are ... ####

  * s = Spot price of underlying stock/asset
  * k = Strike price
  * r = Risk free annual interest rate continuously compounded
  * t = Time in years until option expiration (maturity)
  * v = Implied volatility of returns of underlying stock/asset



#### The option greeks are … ####

  * **_Delta_** - measures the rate of change of option value with respect to changes in the underlying asset's price. Measures the exposure of option price to movement of underlying stock price

  * **_Vega_** - measures sensitivity to volatility. Measures the exposure of the option price to changes in volatility of the underlying

  * **_Theta_** - measures the sensitivity of the value of the derivative to the passage of time. Measures the exposure of the option price to the passage of time

  * **_Rho_** - measures sensitivity to the interest rate: it is the derivative of the option value with respect to the risk free interest rate

  * **_Lambda_** - omega, or elasticity is the percentage change in option value per percentage change in the underlying price, a measure of leverage, sometimes called gearing.

  * _**Gamma**_ - measures the rate of change in the delta with respect to changes in the underlying price.  Measures the exposure of the option delta to the movement of the underlying stock price



---


An interesting article from the BBC [here](http://www.bbc.com/news/magazine-17866646), with companion video below.

<a href='http://www.youtube.com/watch?feature=player_embedded&v=lmvxZgnwwD4' target='_blank'><img src='http://img.youtube.com/vi/lmvxZgnwwD4/0.jpg' width='425' height=344 /></a>