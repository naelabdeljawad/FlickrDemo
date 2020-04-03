function() {
  var env = karate.env;
  if (!env) { env = 'staging'; }  // default when karate.env not set

  // base config
  var config = {
    env: env,
    baseUrl: 'https://www.google.com',
    apiKey: ''
  }
  //switch environment
  if (env == 'staging') {
  config.baseUrl = 'https://www.google.com';
  config.apiKey  = 'fake-xxxxx';
  } 
  else if (env == 'production') { //production environment settings
  config.baseUrl = 'https://......';
  config.apiKey  = 'fake-xxxxx';
  }

  karate.log('karate.env =', karate.env);
  karate.log('config.baseUrl =', config.baseUrl);
  
  return config;
}