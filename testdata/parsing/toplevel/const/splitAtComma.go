package main
const ( a,
 b = 1,2
 )
/**-----
Go file
  PackageDeclaration(main)
    PsiElement(KEYWORD_PACKAGE)('package')
    PsiWhiteSpace(' ')
    PsiElement(IDENTIFIER)('main')
  PsiWhiteSpace('\n')
  ConstDeclarationsImpl
    PsiElement(KEYWORD_CONST)('const')
    PsiWhiteSpace(' ')
    PsiElement(()('(')
    PsiWhiteSpace(' ')
    ConstSpecImpl
      LiteralIdentifierImpl
        PsiElement(IDENTIFIER)('a')
      PsiElement(,)(',')
      PsiWhiteSpace('\n')
      PsiWhiteSpace(' ')
      LiteralIdentifierImpl
        PsiElement(IDENTIFIER)('b')
      PsiWhiteSpace(' ')
      PsiElement(=)('=')
      PsiWhiteSpace(' ')
      LiteralExpressionImpl
        LiteralIntegerImpl
          PsiElement(LITERAL_INT)('1')
      PsiElement(,)(',')
      LiteralExpressionImpl
        LiteralIntegerImpl
          PsiElement(LITERAL_INT)('2')
    PsiWhiteSpace('\n')
    PsiWhiteSpace(' ')
    PsiElement())(')')