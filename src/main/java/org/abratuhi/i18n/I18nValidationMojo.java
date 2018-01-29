package org.abratuhi.i18n;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.IOException;
import java.text.MessageFormat;

@Mojo(name = "i18n-resource-validator", threadSafe = true)
public class I18nValidationMojo extends AbstractMojo {

    @Parameter(name = "encoding", defaultValue = "UTF-8")
    private String encoding;

    @Parameter(name = "basenameRegex", defaultValue = "src/main/resources/locale.*.properties")
    private String basenameRegex;

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            boolean isConsistent = new I18nPropertiesValidator().isConsistent(basenameRegex, getLog());
            if (!isConsistent) {
                throw new MojoFailureException(MessageFormat.format("Resource bundle specified by {0} is not consistent, see previous error output for details on missing keys", basenameRegex));
            }
        } catch (IOException e) {
            throw new MojoExecutionException(MessageFormat.format("Error parsing resource bundle specified by {0}", basenameRegex), e);
        }
    }
}
