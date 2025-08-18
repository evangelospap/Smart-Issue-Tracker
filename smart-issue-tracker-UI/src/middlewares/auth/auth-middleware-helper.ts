// src/middlewares/auth/auth-middleware-helper.ts
import { SignedInAuthObject } from "@clerk/backend/internal";
import { NextRequest } from "next/server";

export async function runCustomAuthLogic(req: NextRequest, session:SignedInAuthObject) {
  console.log("🔐 Running Clerk auth logic...");
  // This runs ONLY on the server (no React hooks allowed!)
  // Example: log headers (debug only!)
  if (process.env.NODE_ENV === "development") {
    console.log("📡 Incoming request:", req.url);
    console.log("🔐 Auth header:", req.headers.get("authorization"));
  }
    // 👉 Example: log the JWT (only in dev!)
    if (process.env.NODE_ENV === "development") {
      // Example: log the session claims (only in dev!)
      const jwt = await session.getToken();
      console.log("🔑 Clerk session jwt:", jwt);
    }

}