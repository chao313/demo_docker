/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package demo.spring.boot.docker.config.security;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A {@link CsrfTokenRepository} that stores the {@link CsrfToken} in the
 * {@link HttpSession}.
 *
 * @author Rob Winch
 * @since 3.2
 * copy by whc
 */
@Component
public class MyHttpSessionCsrfTokenRepository implements CsrfTokenRepository {
    private static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";

    private static final String DEFAULT_CSRF_HEADER_NAME = "X-CSRF-TOKEN";

    private static final String DEFAULT_CSRF_TOKEN_ATTR_NAME = HttpSessionCsrfTokenRepository.class
            .getName().concat(".CSRF_TOKEN");

    private String parameterName = DEFAULT_CSRF_PARAMETER_NAME;

    private String headerName = DEFAULT_CSRF_HEADER_NAME;

    private String sessionAttributeName = DEFAULT_CSRF_TOKEN_ATTR_NAME;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.csrf.CsrfTokenRepository#saveToken(org.
     * springframework .security.web.csrf.CsrfToken,
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void saveToken(CsrfToken token, HttpServletRequest request,
                          HttpServletResponse response) {
        if (token == null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(this.sessionAttributeName);
            }
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(this.sessionAttributeName, token);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.security.web.csrf.CsrfTokenRepository#loadToken(javax.servlet
     * .http.HttpServletRequest)
     */
    public CsrfToken loadToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (CsrfToken) session.getAttribute(this.sessionAttributeName);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.csrf.CsrfTokenRepository#generateToken(javax.
     * servlet .http.HttpServletRequest)
     */
    public CsrfToken generateToken(HttpServletRequest request) {
        return new DefaultCsrfToken(this.headerName, this.parameterName,
                createNewToken());
    }

    /**
     * 生成 CsrfToken 如果存在就返回之前的 CsrfToken
     */
    public CsrfToken generateTokenNotReplace(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (null != session) {
            CsrfToken token = (CsrfToken) session.getAttribute(this.sessionAttributeName);
            if (null != token) {
                return token;
            }
        }
        return new DefaultCsrfToken(this.headerName, this.parameterName,
                createNewToken());
    }

    /**
     * Sets the {@link HttpServletRequest} parameter name that the {@link CsrfToken} is
     * expected to appear on
     *
     * @param parameterName the new parameter name to use
     */
    public void setParameterName(String parameterName) {
        Assert.hasLength(parameterName, "parameterName cannot be null or empty");
        this.parameterName = parameterName;
    }

    /**
     * Sets the header name that the {@link CsrfToken} is expected to appear on and the
     * header that the response will contain the {@link CsrfToken}.
     *
     * @param headerName the new header name to use
     */
    public void setHeaderName(String headerName) {
        Assert.hasLength(headerName, "headerName cannot be null or empty");
        this.headerName = headerName;
    }

    /**
     * Sets the {@link HttpSession} attribute name that the {@link CsrfToken} is stored in
     *
     * @param sessionAttributeName the new attribute name to use
     */
    public void setSessionAttributeName(String sessionAttributeName) {
        Assert.hasLength(sessionAttributeName,
                "sessionAttributename cannot be null or empty");
        this.sessionAttributeName = sessionAttributeName;
    }

    private String createNewToken() {
        return UUID.randomUUID().toString();
    }
}