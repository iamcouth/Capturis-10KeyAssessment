package com.capturis.tenkeyassessment.webapp;
import com.capturis.tenkeyassessment.assessment.api.AssessmentResource;
import com.capturis.tenkeyassessment.assessmentresult.api.AssessmentResultResource;
import com.capturis.tenkeyassessment.health.api.HealthResource;
import com.capturis.tenkeyassessment.login.api.LoginResource;
import com.capturis.tenkeyassessment.register.api.RegisterResource;
import com.google.inject.servlet.ServletModule;
public class GuiceConfig extends ServletModule {
  @Override
  protected void configureServlets() {

    bind(HealthResource.class);
    bind(RegisterResource.class);
    bind(LoginResource.class);
    bind(AssessmentResource.class);
    bind(AssessmentResultResource.class);
    filter("*").through(CORSFilter.class);
  }
}
