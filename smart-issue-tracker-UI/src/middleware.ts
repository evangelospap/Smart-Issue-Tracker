import { runCustomAuthLogic } from './middlewares/auth/auth-middleware-helper'
import { clerkMiddleware, createRouteMatcher } from '@clerk/nextjs/server'

const isProtectedRoute = createRouteMatcher(['/dashboard(.*)', '/issues(.*)'])


export default clerkMiddleware(async (auth, req) => {
  if (isProtectedRoute(req)){
    const session = await auth.protect();
      // Run your pre-Clerk custom logic (logging, tracing, etc.)
    runCustomAuthLogic(req,session);
    
  } 
  
})

export const config = {
  matcher: [
    // Skip Next.js internals and all static files, unless found in search params
    '/((?!_next|[^?]*\\.(?:html?|css|js(?!on)|jpe?g|webp|png|gif|svg|ttf|woff2?|ico|csv|docx?|xlsx?|zip|webmanifest)).*)',
    // Always run for API routes
    '/(api|trpc)(.*)',
  ],
}