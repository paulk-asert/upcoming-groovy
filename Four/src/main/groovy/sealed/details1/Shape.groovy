package sealed.details1

import groovy.transform.Sealed

@Sealed(permittedSubclasses=[Diamond,Circle]) class Shape { }
