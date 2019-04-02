package com.soartech.soarls;

import java.util.HashMap;
import java.util.Map;

/** The results of analysing a Soar project starting from a particular
 * entry point.
 *
 * It is common for a Soar codebase to contain multiple entry points,
 * where each entry point loads a common set of Soar code. However,
 * even though they share code, the shared code is being loaded with
 * respect to a different interpreter state each time. The
 * dramatically limits or eliminates the possibility to share analysis
 * data.
 */
class ProjectAnalysis {
    final String entryPointUri;

    final Map<String, FileAnalysis> files = new HashMap<>();

    ProjectAnalysis(String entryPointUri) {
        this.entryPointUri = entryPointUri;
    }
}