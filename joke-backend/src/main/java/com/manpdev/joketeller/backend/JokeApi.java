
package com.manpdev.joketeller.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.joketeller.repository.JokeTellerRepository;

/** An endpoint class we are exposing */
@Api(
  name = "jokeApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.joketeller.manpdev.com",
    ownerName = "backend.joketeller.manpdev.com",
    packagePath=""
  )
)
public class JokeApi {

    @ApiMethod(httpMethod = ApiMethod.HttpMethod.GET, name = "getJoke")
    public JokeModel getJoke() {
        JokeModel response = new JokeModel();
        response.setJoke(JokeTellerRepository.getJoke());
        return response;
    }
}
