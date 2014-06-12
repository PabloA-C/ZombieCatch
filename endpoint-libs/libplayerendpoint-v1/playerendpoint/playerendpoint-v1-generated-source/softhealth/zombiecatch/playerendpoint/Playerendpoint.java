/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-06-09 16:41:44 UTC)
 * on 2014-06-11 at 22:44:54 UTC 
 * Modify at your own risk.
 */

package softhealth.zombiecatch.playerendpoint;

/**
 * Service definition for Playerendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link PlayerendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Playerendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the playerendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://ZombieCatchServer.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "playerendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Playerendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Playerendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getPlayer".
   *
   * This request holds the parameters needed by the the playerendpoint server.  After setting any
   * optional parameters, call the {@link GetPlayer#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public GetPlayer getPlayer(java.lang.Long id) throws java.io.IOException {
    GetPlayer result = new GetPlayer(id);
    initialize(result);
    return result;
  }

  public class GetPlayer extends PlayerendpointRequest<softhealth.zombiecatch.playerendpoint.model.Player> {

    private static final String REST_PATH = "player/{id}";

    /**
     * Create a request for the method "getPlayer".
     *
     * This request holds the parameters needed by the the playerendpoint server.  After setting any
     * optional parameters, call the {@link GetPlayer#execute()} method to invoke the remote
     * operation. <p> {@link
     * GetPlayer#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetPlayer(java.lang.Long id) {
      super(Playerendpoint.this, "GET", REST_PATH, null, softhealth.zombiecatch.playerendpoint.model.Player.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetPlayer setAlt(java.lang.String alt) {
      return (GetPlayer) super.setAlt(alt);
    }

    @Override
    public GetPlayer setFields(java.lang.String fields) {
      return (GetPlayer) super.setFields(fields);
    }

    @Override
    public GetPlayer setKey(java.lang.String key) {
      return (GetPlayer) super.setKey(key);
    }

    @Override
    public GetPlayer setOauthToken(java.lang.String oauthToken) {
      return (GetPlayer) super.setOauthToken(oauthToken);
    }

    @Override
    public GetPlayer setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetPlayer) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetPlayer setQuotaUser(java.lang.String quotaUser) {
      return (GetPlayer) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetPlayer setUserIp(java.lang.String userIp) {
      return (GetPlayer) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public GetPlayer setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public GetPlayer set(String parameterName, Object value) {
      return (GetPlayer) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertPlayer".
   *
   * This request holds the parameters needed by the the playerendpoint server.  After setting any
   * optional parameters, call the {@link InsertPlayer#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link softhealth.zombiecatch.playerendpoint.model.Player}
   * @return the request
   */
  public InsertPlayer insertPlayer(softhealth.zombiecatch.playerendpoint.model.Player content) throws java.io.IOException {
    InsertPlayer result = new InsertPlayer(content);
    initialize(result);
    return result;
  }

  public class InsertPlayer extends PlayerendpointRequest<softhealth.zombiecatch.playerendpoint.model.Player> {

    private static final String REST_PATH = "player";

    /**
     * Create a request for the method "insertPlayer".
     *
     * This request holds the parameters needed by the the playerendpoint server.  After setting any
     * optional parameters, call the {@link InsertPlayer#execute()} method to invoke the remote
     * operation. <p> {@link
     * InsertPlayer#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link softhealth.zombiecatch.playerendpoint.model.Player}
     * @since 1.13
     */
    protected InsertPlayer(softhealth.zombiecatch.playerendpoint.model.Player content) {
      super(Playerendpoint.this, "POST", REST_PATH, content, softhealth.zombiecatch.playerendpoint.model.Player.class);
    }

    @Override
    public InsertPlayer setAlt(java.lang.String alt) {
      return (InsertPlayer) super.setAlt(alt);
    }

    @Override
    public InsertPlayer setFields(java.lang.String fields) {
      return (InsertPlayer) super.setFields(fields);
    }

    @Override
    public InsertPlayer setKey(java.lang.String key) {
      return (InsertPlayer) super.setKey(key);
    }

    @Override
    public InsertPlayer setOauthToken(java.lang.String oauthToken) {
      return (InsertPlayer) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertPlayer setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertPlayer) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertPlayer setQuotaUser(java.lang.String quotaUser) {
      return (InsertPlayer) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertPlayer setUserIp(java.lang.String userIp) {
      return (InsertPlayer) super.setUserIp(userIp);
    }

    @Override
    public InsertPlayer set(String parameterName, Object value) {
      return (InsertPlayer) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listPlayer".
   *
   * This request holds the parameters needed by the the playerendpoint server.  After setting any
   * optional parameters, call the {@link ListPlayer#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListPlayer listPlayer() throws java.io.IOException {
    ListPlayer result = new ListPlayer();
    initialize(result);
    return result;
  }

  public class ListPlayer extends PlayerendpointRequest<softhealth.zombiecatch.playerendpoint.model.CollectionResponsePlayer> {

    private static final String REST_PATH = "player";

    /**
     * Create a request for the method "listPlayer".
     *
     * This request holds the parameters needed by the the playerendpoint server.  After setting any
     * optional parameters, call the {@link ListPlayer#execute()} method to invoke the remote
     * operation. <p> {@link
     * ListPlayer#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListPlayer() {
      super(Playerendpoint.this, "GET", REST_PATH, null, softhealth.zombiecatch.playerendpoint.model.CollectionResponsePlayer.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListPlayer setAlt(java.lang.String alt) {
      return (ListPlayer) super.setAlt(alt);
    }

    @Override
    public ListPlayer setFields(java.lang.String fields) {
      return (ListPlayer) super.setFields(fields);
    }

    @Override
    public ListPlayer setKey(java.lang.String key) {
      return (ListPlayer) super.setKey(key);
    }

    @Override
    public ListPlayer setOauthToken(java.lang.String oauthToken) {
      return (ListPlayer) super.setOauthToken(oauthToken);
    }

    @Override
    public ListPlayer setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListPlayer) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListPlayer setQuotaUser(java.lang.String quotaUser) {
      return (ListPlayer) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListPlayer setUserIp(java.lang.String userIp) {
      return (ListPlayer) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListPlayer setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Integer limit;

    /**

     */
    public java.lang.Integer getLimit() {
      return limit;
    }

    public ListPlayer setLimit(java.lang.Integer limit) {
      this.limit = limit;
      return this;
    }

    @Override
    public ListPlayer set(String parameterName, Object value) {
      return (ListPlayer) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removePlayer".
   *
   * This request holds the parameters needed by the the playerendpoint server.  After setting any
   * optional parameters, call the {@link RemovePlayer#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public RemovePlayer removePlayer(java.lang.Long id) throws java.io.IOException {
    RemovePlayer result = new RemovePlayer(id);
    initialize(result);
    return result;
  }

  public class RemovePlayer extends PlayerendpointRequest<Void> {

    private static final String REST_PATH = "player/{id}";

    /**
     * Create a request for the method "removePlayer".
     *
     * This request holds the parameters needed by the the playerendpoint server.  After setting any
     * optional parameters, call the {@link RemovePlayer#execute()} method to invoke the remote
     * operation. <p> {@link
     * RemovePlayer#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemovePlayer(java.lang.Long id) {
      super(Playerendpoint.this, "DELETE", REST_PATH, null, Void.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemovePlayer setAlt(java.lang.String alt) {
      return (RemovePlayer) super.setAlt(alt);
    }

    @Override
    public RemovePlayer setFields(java.lang.String fields) {
      return (RemovePlayer) super.setFields(fields);
    }

    @Override
    public RemovePlayer setKey(java.lang.String key) {
      return (RemovePlayer) super.setKey(key);
    }

    @Override
    public RemovePlayer setOauthToken(java.lang.String oauthToken) {
      return (RemovePlayer) super.setOauthToken(oauthToken);
    }

    @Override
    public RemovePlayer setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemovePlayer) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemovePlayer setQuotaUser(java.lang.String quotaUser) {
      return (RemovePlayer) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemovePlayer setUserIp(java.lang.String userIp) {
      return (RemovePlayer) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemovePlayer setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemovePlayer set(String parameterName, Object value) {
      return (RemovePlayer) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updatePlayer".
   *
   * This request holds the parameters needed by the the playerendpoint server.  After setting any
   * optional parameters, call the {@link UpdatePlayer#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link softhealth.zombiecatch.playerendpoint.model.Player}
   * @return the request
   */
  public UpdatePlayer updatePlayer(softhealth.zombiecatch.playerendpoint.model.Player content) throws java.io.IOException {
    UpdatePlayer result = new UpdatePlayer(content);
    initialize(result);
    return result;
  }

  public class UpdatePlayer extends PlayerendpointRequest<softhealth.zombiecatch.playerendpoint.model.Player> {

    private static final String REST_PATH = "player";

    /**
     * Create a request for the method "updatePlayer".
     *
     * This request holds the parameters needed by the the playerendpoint server.  After setting any
     * optional parameters, call the {@link UpdatePlayer#execute()} method to invoke the remote
     * operation. <p> {@link
     * UpdatePlayer#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link softhealth.zombiecatch.playerendpoint.model.Player}
     * @since 1.13
     */
    protected UpdatePlayer(softhealth.zombiecatch.playerendpoint.model.Player content) {
      super(Playerendpoint.this, "PUT", REST_PATH, content, softhealth.zombiecatch.playerendpoint.model.Player.class);
    }

    @Override
    public UpdatePlayer setAlt(java.lang.String alt) {
      return (UpdatePlayer) super.setAlt(alt);
    }

    @Override
    public UpdatePlayer setFields(java.lang.String fields) {
      return (UpdatePlayer) super.setFields(fields);
    }

    @Override
    public UpdatePlayer setKey(java.lang.String key) {
      return (UpdatePlayer) super.setKey(key);
    }

    @Override
    public UpdatePlayer setOauthToken(java.lang.String oauthToken) {
      return (UpdatePlayer) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdatePlayer setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdatePlayer) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdatePlayer setQuotaUser(java.lang.String quotaUser) {
      return (UpdatePlayer) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdatePlayer setUserIp(java.lang.String userIp) {
      return (UpdatePlayer) super.setUserIp(userIp);
    }

    @Override
    public UpdatePlayer set(String parameterName, Object value) {
      return (UpdatePlayer) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Playerendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Playerendpoint}. */
    @Override
    public Playerendpoint build() {
      return new Playerendpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link PlayerendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setPlayerendpointRequestInitializer(
        PlayerendpointRequestInitializer playerendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(playerendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
